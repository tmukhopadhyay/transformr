// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

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

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
