import { TestBed } from '@angular/core/testing';

import { RouterGuardRecruiterService } from './router-guard-recruiter.service';

describe('RouterGuardRecruiterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RouterGuardRecruiterService = TestBed.get(RouterGuardRecruiterService);
    expect(service).toBeTruthy();
  });
});
