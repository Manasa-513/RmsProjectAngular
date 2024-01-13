import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { FormArrayName, FormBuilder } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from 'src/app/auth.services/auth.service/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  isSpinning: boolean;
  validateForm: FormGroup;
  

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.get("password").value) {
      return { confirm: true, error: true };
    }
    return {};
  };
  
  


  constructor(private service: AuthService,
    private fb: FormBuilder,
    private notification:NzNotificationService){}
    ngOnInit(){
      this.validateForm = this.fb.group({
        email:["",Validators.required],
        password:["",Validators.required],
        checkPassword:["",[Validators.required ,this.confirmationValidator]],
        name:["",Validators.required],
      })
    }
    register() {
      console.log(this.validateForm.value);
    
      // Pass only the form values to the service
      this.service.signup(this.validateForm.value).subscribe((res) => {
        console.log(res);
        if(res.id !=null){
          this.notification.success("SUCCESS","you are registered successfully",{nzDuration:5000});
        }else{
          this.notification.success("ERROR","something went wrong",{nzDuration:5000});
        }
      });
    }
    

  }
