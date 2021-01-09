import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import * as moment from 'moment';

import { TimeZone } from '../../models';
import { DataStore } from '../../store';

@Component({
  selector: 'app-time',
  templateUrl: './time.component.html',
  styleUrls: ['./time.component.scss']
})
export class TimeComponent implements OnInit {

  @Input() public isMobile: boolean = false;

  public timeZones: Array<TimeZone> = [];
  public fromTimeZone: TimeZone | undefined;
  public toTimeZone: TimeZone | undefined;
  public timeConverterForm = new FormGroup({
    fromTime: new FormControl(''),
    fromTimeFormatted: new FormControl(''),
    toTime: new FormControl(''),
    toTimeFormatted: new FormControl(''),
    fromZoneId: new FormControl(''),
    toZoneId: new FormControl('')
  });

  constructor(private dataStore: DataStore) { }

  ngOnInit() {
    this.dataStore.timeZones$.subscribe((timeZones: Array<TimeZone>) => this.timeZones = timeZones);
  }

  public exchangeValues() {
    const formValue = this.timeConverterForm.value;
    this.timeConverterForm.controls['fromZoneId'].setValue(formValue.toZoneId);
    this.timeConverterForm.controls['toZoneId'].setValue(formValue.fromZoneId);
  }

  public onSubmit() {
    const formValue = this.timeConverterForm.value;
    const fromTime = moment(formValue.fromTime, 'YYYY-MM-DDTHH:mm', true);

    this.fromTimeZone = this.timeZones.find(t => t.id === formValue.fromZoneId);
    this.toTimeZone = this.timeZones.find(t => t.id === formValue.toZoneId);

    const toTime = fromTime.clone().add((this.toTimeZone?.offset || 0) - (this.fromTimeZone?.offset || 0), 'h');
    this.timeConverterForm.controls['toTime'].setValue(toTime.format('YYYY-MM-DDTHH:mm'));

    if(fromTime.year() !== toTime.year()) {
      this.timeConverterForm.controls['fromTimeFormatted'].setValue(fromTime.format('MMM D, YYYY, h:mm A'));
      this.timeConverterForm.controls['toTimeFormatted'].setValue(toTime.format('MMM D, YYYY, h:mm A'));
    } else {
      this.timeConverterForm.controls['fromTimeFormatted'].setValue(fromTime.format('MMM D, h:mm A'));
      this.timeConverterForm.controls['toTimeFormatted'].setValue(toTime.format('MMM D, h:mm A'));
    }
  }
}
