import { Component } from '@angular/core';

import { UtilityService } from '../services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent {

  public isLoading = true;
  public isMobile: boolean;

  constructor(private utilityService: UtilityService) {
    this.isMobile = this.utilityService.isMobile();
  }

  public onRouteActivate() {
    this.isLoading = false;
  }
}
