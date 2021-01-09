import { CurrencyRate } from './currency-rate';

export interface ExchangeRate {
  from: CurrencyRate;
  to: CurrencyRate;
  date: string;
}
