import { Injectable } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';

import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UtilityService {

  public mobileQuery: MediaQueryList;

  constructor(private media: MediaMatcher) {
    this.mobileQuery = this.media.matchMedia(
      '(max-width: ' + environment.mobileMaxWidth + 'px)'
    );
  }

  public isMobile(): boolean {
    return this.mobileQuery.matches;
  }
}
