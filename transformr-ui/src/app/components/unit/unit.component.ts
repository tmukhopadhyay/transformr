import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { UtilityService } from '../../services';
import { MetricUnit, Unit } from '../../models';
import { DataStore } from '../../store';

@Component({
  selector: 'app-unit',
  templateUrl: './unit.component.html',
  styleUrls: ['./unit.component.scss']
})
export class UnitComponent implements OnInit {

  @Input() public type: string | undefined;

  public isMobile: boolean;
  public title: string = '';
  public subTitle: string = '';
  public icon: string = '';
  public metricUnit: MetricUnit = null as unknown as MetricUnit;
  public units: Array<Unit> = [];
  public baseUnit: Unit | undefined;
  public fromUnit: Unit | undefined;
  public toUnit: Unit | undefined;
  public unitConverterForm = new FormGroup({
    fromUnitId: new FormControl(''),
    fromUnitValue: new FormControl(''),
    toUnitId: new FormControl(''),
    toUnitValue: new FormControl('')
  });

  constructor(
    private route: ActivatedRoute,
    private dataStore: DataStore,
    private utilityService: UtilityService
  ) {
    this.isMobile = this.utilityService.isMobile();
  }

  ngOnInit() {
    this.route.data.subscribe(data => {
      if(data && data.type) {
        this.type = data.type;
      }
    });
    this.dataStore.metricUnit$.subscribe(metricUnit => {
      this.metricUnit = metricUnit;
      this.populateData();
      this.baseUnit = this.units.find(u => u.base);
    });
  }

  private populateData() {
    switch(this.type) {
      case 'length':
        this.icon = 'open_in_full';
        this.title = 'Length converter';
        this.subTitle = 'Convert lengths';
        this.units = this.metricUnit.lengths;
        break;
      case 'area':
        this.icon = 'aspect_ratio';
        this.title = 'Area converter';
        this.subTitle = 'Convert areas';
        this.units = this.metricUnit.areas;
        break;
      case 'volume':
        this.icon = 'ac_unit';
        this.title = 'Volume converter';
        this.subTitle = 'Convert volumes';
        this.units = this.metricUnit.volumes;
        break;
      case 'mass':
        this.icon = 'fitness_center';
        this.title = 'Mass converter';
        this.subTitle = 'Convert masses';
        this.units = this.metricUnit.masses;
        break;
      case 'temperature':
        this.icon = 'equalizer';
        this.title = 'Temperature converter';
        this.subTitle = 'Convert temperatures';
        this.units = this.metricUnit.temperatures;
        break;
    }
  }

  public exchangeValues() {
    const formValue = this.unitConverterForm.value;
    this.unitConverterForm.controls['fromUnitId'].setValue(formValue.toUnitId);
    this.unitConverterForm.controls['toUnitId'].setValue(formValue.fromUnitId);
  }

  public onSubmit() {
    const formValue = this.unitConverterForm.getRawValue();

    this.fromUnit = this.units.find(u => u.id == formValue.fromUnitId);
    this.toUnit = this.units.find(u => u.id == formValue.toUnitId);

    var baseValue = 0;
    if(this.fromUnit?.fromFormula) {
      baseValue = eval(this.fromUnit?.fromFormula.replace('x', formValue.fromUnitValue));
    } else {
      baseValue = (this.fromUnit?.value || 0) * formValue.fromUnitValue;
    }

    if(this.toUnit?.toFormula) {
      const tovalue = eval(this.toUnit?.toFormula.replace('x', String(baseValue)));
      this.unitConverterForm.controls['toUnitValue'].setValue(tovalue);
    } else {
      this.unitConverterForm.controls['toUnitValue'].setValue(baseValue / (this.toUnit?.value || 0));
    }
  }
}
