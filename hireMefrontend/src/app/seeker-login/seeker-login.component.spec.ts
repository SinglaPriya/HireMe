import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SeekerLoginComponent } from './seeker-login.component';

describe('SeekerLoginComponent', () => {
  let component: SeekerLoginComponent;
  let fixture: ComponentFixture<SeekerLoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SeekerLoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SeekerLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
