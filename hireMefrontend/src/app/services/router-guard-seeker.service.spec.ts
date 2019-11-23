import { TestBed } from '@angular/core/testing';

import { RouterGuardSeekerService } from './router-guard-seeker.service';

describe('RouterGuardSeekerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RouterGuardSeekerService = TestBed.get(RouterGuardSeekerService);
    expect(service).toBeTruthy();
  });
});
