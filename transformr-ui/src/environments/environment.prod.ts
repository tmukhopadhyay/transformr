const serverBaseUrl = 'http://localhost:8001/api';

export const environment = {
  mobileMaxWidth: 768,
  production: true,
  url: {
    currencyList: serverBaseUrl + '/currency',
    exchangeRate: serverBaseUrl + '/currency/exchange',
    timeZones: serverBaseUrl + '/time/zones',
    unitMetrics: serverBaseUrl + '/unit/metrics'
  }
};
