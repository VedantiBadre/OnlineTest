import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User=new User();
  constructor(private userService: UserService) { }

  ngOnInit(): void {
}

  userRegister(){
    console.log(this.user);
    this.userService.registerUser(this.user).subscribe(data=>{
    alert("registered");
    })
 }
}
