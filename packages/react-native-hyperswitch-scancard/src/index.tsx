import React from 'react';
import {
  NativeModules,
  TouchableOpacity,
  StyleProp,
  ViewStyle,
} from 'react-native';

console.log('react-native-scancard loaded');

const HyperswitchScancard = NativeModules.HyperswitchScancard || null;

const isAvailable = HyperswitchScancard && HyperswitchScancard.launchScanCard;

export interface ScanCardReturnType {
  status: string;
  data?: ScanCardData;
}

interface ScanCardData {
  pan: string;
  expiryMonth: string;
  expiryYear: string;
}

function launchScanCard(callback: (s: ScanCardReturnType) => void): void {
  if (isAvailable) {
    return HyperswitchScancard.launchScanCard(
      '',
      (response: Record<string, any>) => {
        const status = response.status || 'Default';
        const data: ScanCardData | undefined = response.data;
        const scanData: ScanCardReturnType = {
          status,
          data: data
            ? {
                pan: data.pan || '',
                expiryMonth: data.expiryMonth || '',
                expiryYear: data.expiryYear || '',
              }
            : undefined,
        };
        callback(scanData);
      }
    );
  }
}

interface ScanCardProps {
  callback: (data: ScanCardReturnType) => void;
  style?: StyleProp<ViewStyle> | undefined;
}

const ScanCardComponent: React.FC<ScanCardProps> = ({
  children,
  callback,
  style,
}) => {
  if (isAvailable) {
    return (
      <TouchableOpacity onPress={() => launchScanCard(callback)} style={style}>
        {children}
      </TouchableOpacity>
    );
  } else {
    console.warn('Scan Card feature unavailable');
    return null;
  }
};

export { ScanCardComponent, isAvailable, launchScanCard };
