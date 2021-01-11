import { Component, ViewEncapsulation } from '@angular/core';

import { UtilityService } from '../../services';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent {

  public isMobile: boolean;

  constructor(private utilityService: UtilityService) {
    this.isMobile = this.utilityService.isMobile();
  }
}
