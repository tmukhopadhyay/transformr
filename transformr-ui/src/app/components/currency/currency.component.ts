import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { Currency, ExchangeRate } from '../../models';
import { DataStore } from '../../store';
import { HttpService, UtilityService } from '../../services';

@Component({
  selector: 'app-currency',
  templateUrl: './currency.component.html',
  styleUrls: ['./currency.component.scss'],
})
export class CurrencyComponent implements OnInit {

  public isMobile: boolean;
  public currencyList$: Observable<Array<Currency>> = of([]);
  public exchangeRate: ExchangeRate | undefined;
  public currencyExchangeForm = new FormGroup({
    fromAmount: new FormControl(''),
    fromCurrency: new FormControl(''),
    toCurrency: new FormControl(''),
  });

  constructor(
    private dataStore: DataStore,
    private httpService: HttpService,
    private utilityService: UtilityService
  ) {
    this.isMobile = this.utilityService.isMobile();
  }

  ngOnInit() {
    this.currencyList$ = this.dataStore.currencyList$;
  }

  public exchangeValues() {
    const formValue = this.currencyExchangeForm.value;
    this.currencyExchangeForm.controls['fromCurrency'].setValue(formValue.toCurrency);
    this.currencyExchangeForm.controls['toCurrency'].setValue(formValue.fromCurrency);
  }

  public onSubmit() {
    const formValue = this.currencyExchangeForm.value;
    this
      .httpService
      .getExchangeRate(formValue.fromCurrency, formValue.toCurrency)
      .subscribe((exchangeRate: ExchangeRate) => {
        this.exchangeRate = {
          from: {
            currency: exchangeRate.from.currency,
            value: formValue.fromAmount
          },
          to: {
            currency: exchangeRate.to.currency,
            value: exchangeRate.to.value * formValue.fromAmount
          },
          date: exchangeRate.date
        };
      });
  }
}
