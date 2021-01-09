import { Injectable } from '@angular/core';
import { forkJoin, Observable, ReplaySubject } from 'rxjs';
import { map } from 'rxjs/operators';
import { Currency, MetricUnit, TimeZone } from '../models';
import { HttpService } from '../services';

@Injectable({
  providedIn: 'root',
})
export class DataStore {
  private currencyList = new ReplaySubject<Array<Currency>>(1);
  public currencyList$ = this.currencyList.asObservable();
  private metricUnit = new ReplaySubject<MetricUnit>(1);
  public metricUnit$ = this.metricUnit.asObservable();
  private timeZones = new ReplaySubject<Array<TimeZone>>(1);
  public timeZones$ = this.timeZones.asObservable();
  private dataLoaded = false;

  constructor(private httpService: HttpService) {}

  public setCurrencyList(currencyList: Array<Currency>) {
    this.currencyList.next(currencyList);
  }

  public setMetricUnit(metricUnit: MetricUnit) {
    this.metricUnit.next(metricUnit);
  }

  public setTimeZones(timeZones: Array<TimeZone>) {
    this.timeZones.next(timeZones);
  }

  public setDataLoaded() {
    this.dataLoaded = true;
  }

  public isDataLoaded() {
    return this.dataLoaded;
  }

  public loadData(): Observable<boolean> {
    const currencyList = this.httpService.getCurrencyList();
    const timeZoneList = this.httpService.getTimeZoneList();
    const unitMetrics = this.httpService.getUnitMetrics();
    return forkJoin([currencyList, timeZoneList, unitMetrics]).pipe(
      map((results) => {
        this.setCurrencyList(results[0]);
        this.setMetricUnit(results[2]);
        this.setTimeZones(results[1]);
        this.setDataLoaded();
        return true;
      })
    );
  }
}
