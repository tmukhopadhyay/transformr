const serverBaseUrl = 'assets/data/';

export const environment = {
  mobileMaxWidth: 768,
  production: false,
  url: {
    currencyList: serverBaseUrl + '/currency-list.json',
    exchangeRate: serverBaseUrl + '/exchange-rate.json',
    timeZones: serverBaseUrl + '/time-zones.json',
    unitMetrics: serverBaseUrl + '/metric-units.json'
  }
};
