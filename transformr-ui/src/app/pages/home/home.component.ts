import { Component, ViewEncapsulation } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';

import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent {
  public mobileQuery: MediaQueryList;

  constructor(private media: MediaMatcher) {
    this.mobileQuery = this.media.matchMedia(
      '(max-width: ' + environment.mobileMaxWidth + 'px)'
    );
  }
}
