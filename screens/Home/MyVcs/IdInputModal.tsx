import React from 'react';
import {Icon, Input} from 'react-native-elements';
import {Picker} from '@react-native-picker/picker';
import {Button, Column, Row, Text} from '../../../components/ui';
import {Modal} from '../../../components/ui/Modal';
import {Theme} from '../../../components/ui/styleUtils';
import {IdInputModalProps, useIdInputModal} from './IdInputModalController';
import {useTranslation} from 'react-i18next';
import {
  I18nManager,
  KeyboardAvoidingView,
  TextInput,
  TouchableOpacity,
  Dimensions,
} from 'react-native';
import {
  individualId,
  isIOS,
  GET_INDIVIDUAL_ID,
} from '../../../shared/constants';
import {MessageOverlay} from '../../../components/MessageOverlay';
import testIDProps from '../../../shared/commonUtil';
import {CustomTooltip} from '../../../components/ui/ToolTip';

export const IdInputModal: React.FC<IdInputModalProps> = props => {
  const {t} = useTranslation('IdInputModal');
  const controller = useIdInputModal(props);

  const setIndividualID = () => {
    controller.SET_INDIVIDUAL_ID(individualId);
  };

  const dismissInput = () => {
    props.onDismiss();
    GET_INDIVIDUAL_ID({id: '', idType: 'UIN'});
  };

  const inputLabel = t('enterId', {idType: controller.idType});

  const setIdInputRef = (node: TextInput) =>
    !controller.idInputRef && controller.READY(node);

  return (
    <Modal
      onDismiss={dismissInput}
      testID="retrieveIdHeader"
      isVisible={props.isVisible}
      onShow={setIndividualID}
      headerTitle={t('header')}
      headerElevation={2}>
      <KeyboardAvoidingView
        style={Theme.Styles.keyboardAvoidStyle}
        behavior={isIOS() ? 'padding' : 'height'}>
        <Column>
          <Text
            align="left"
            size="regular"
            style={Theme.TextStyles.retrieveIdLabel}>
            {t('guideLabel')}
          </Text>
          <Row crossAlign="center" style={Theme.Styles.idInputContainer}>
            <Column style={Theme.Styles.idInputPicker}>
              <Picker
                {...testIDProps('selectIdTypePicker')}
                selectedValue={controller.idType}
                onValueChange={controller.SELECT_ID_TYPE}
                style={Theme.Styles.picker}>
                <Picker.Item label="UIN" value="UIN" />
                <Picker.Item label="VID" value="VID" />
              </Picker>
            </Column>
            <Column
              align="center"
              style={{
                height: 150,
              }}>
              <Input
                {...testIDProps('idInputModalIndividualId')}
                placeholder={!controller.id ? inputLabel : ''}
                inputContainerStyle={
                  controller.id
                    ? Theme.Styles.idInputBottom
                    : Theme.Styles.idInput
                }
                inputStyle={{
                  textAlign: I18nManager.isRTL ? 'right' : 'left',
                  fontWeight: '700',
                }}
                selectionColor={Theme.Colors.Cursor}
                value={controller.id}
                keyboardType="number-pad"
                rightIcon={
                  <CustomTooltip
                    testID="IdInputToolTip"
                    title={t('toolTipTitle', {idType: controller.idType})}
                    description={t(`toolTip${controller.idType}Description`)}
                    width={Dimensions.get('screen').width * 0.85}
                    height={Dimensions.get('screen').height * 0.18}
                    triggerComponent={
                      <Icon
                        {...testIDProps('IdInputToolTipInfo')}
                        name="infocirlceo"
                        type="antdesign"
                        color={Theme.Colors.tooltipIcon}
                      />
                    }
                  />
                }
                errorStyle={Theme.TextStyles.error}
                errorMessage={controller.idError}
                onChangeText={controller.INPUT_ID}
                ref={setIdInputRef}
              />
            </Column>
          </Row>
        </Column>
        <Column>
          <Button
            testID="generateVc"
            type="gradient"
            title={t('generateVc')}
            disabled={!controller.id}
            margin="24 0 6 0"
            onPress={controller.VALIDATE_INPUT}
            loading={controller.isRequestingOtp}
          />
          {!controller.id && (
            <Row style={Theme.Styles.getId}>
              <Text
                color={Theme.Colors.getVidColor}
                weight="semibold"
                size="small">
                {t('noUIN/VID')}
              </Text>
              <TouchableOpacity activeOpacity={1} onPress={props.onPress}>
                <Text
                  testID="getItNow"
                  color={Theme.Colors.AddIdBtnBg}
                  weight="bold"
                  size="small"
                  margin="0 0 0 5">
                  {t('getItHere')}
                </Text>
              </TouchableOpacity>
            </Row>
          )}
        </Column>

        <MessageOverlay
          isVisible={controller.isRequestingOtp}
          title={t('requestingOTP')}
          progress
        />
      </KeyboardAvoidingView>
    </Modal>
  );
};
