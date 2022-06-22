import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FizzbuzzMainComponent } from './fizzbuzz-main.component';

describe('FizzbuzzMainComponent', () => {
  let component: FizzbuzzMainComponent;
  let fixture: ComponentFixture<FizzbuzzMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FizzbuzzMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FizzbuzzMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
