import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JobsByRecComponent } from './jobs-by-rec.component';

describe('JobsByRecComponent', () => {
  let component: JobsByRecComponent;
  let fixture: ComponentFixture<JobsByRecComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JobsByRecComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobsByRecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
