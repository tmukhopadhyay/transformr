import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Currency, ExchangeRate, TimeZone } from '../models';
import { MetricUnit } from '../models/metric-unit';

@Injectable({
  providedIn: 'root',
})
export class HttpService {

  constructor(private httpClient: HttpClient) {}

  public getCurrencyList(): Observable<Array<Currency>> {
    return this.httpClient.get<Array<Currency>>(environment.url.currencyList);
  }

  public getExchangeRate(from: string, to: string): Observable<ExchangeRate> {
    return this.httpClient.get<ExchangeRate>(environment.url.exchangeRate, {
      params: { from, to },
    });
  }

  public getTimeZoneList(): Observable<Array<TimeZone>> {
    return this.httpClient.get<Array<TimeZone>>(environment.url.timeZones);
  }

  public getUnitMetrics(): Observable<MetricUnit> {
    return this.httpClient.get<MetricUnit>(environment.url.unitMetrics);
  }
}
