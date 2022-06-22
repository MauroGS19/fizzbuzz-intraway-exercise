import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FizzbuzzMainComponent} from "./components/fizzbuzz-main/fizzbuzz-main.component";

const routes: Routes = [{ path: '', component: FizzbuzzMainComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
