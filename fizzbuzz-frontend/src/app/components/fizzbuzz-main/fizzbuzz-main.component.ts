import {Component, OnInit} from '@angular/core';
import {FizzbuzzService} from "../../services/fizzbuzz.service";
import {Operation} from "../../models/operation.model";
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-fizzbuzz-main',
  templateUrl: './fizzbuzz-main.component.html',
  styleUrls: ['./fizzbuzz-main.component.css']
})
export class FizzbuzzMainComponent implements OnInit {

  operation: Operation | undefined;
  fizzbuzzForm!: FormGroup;
  submit!: boolean;
  errorMessage!: string;

  constructor(
    private fizzBuzzService: FizzbuzzService,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.initFormGroup();
    this.submit = false;
  }

  initFormGroup() {
    this.fizzbuzzForm = this.formBuilder.group({
      firstNumber: ['', [Validators.required, Validators.pattern(/^-?\d*\.{0,1}\d+$/)]],
      secondNumber: ['', [Validators.required, Validators.pattern(/^-?\d*\.{0,1}\d+$/)]]
    });
  }

  get firstNumber(): AbstractControl {
    return <AbstractControl<any, any>>this.fizzbuzzForm.get('firstNumber');
  }

  get secondNumber(): AbstractControl {
    return <AbstractControl<any, any>>this.fizzbuzzForm.get('secondNumber');
  }

  onSubmit(): void {
    this.submit = true;
    this.operation = undefined;
    if (this.fizzbuzzForm?.valid) {
      this.fizzBuzzService.getOperations(this.fizzbuzzForm.controls['firstNumber'].value, this.fizzbuzzForm.controls['secondNumber'].value)
        .subscribe(operation => {
          this.operation = operation;
          this.errorMessage = "";
        }, (error) => this.errorMessage = error.error.message);
    } else {
      this.errorMessage = "Los valores ingresados son incorrectos, por favor reintente"
    }
  }

}
