import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {ContactService} from "../../services/contact.service";

@Component({
  selector: 'app-add-contact',
  templateUrl: './add-contact.component.html',
  styleUrls: ['./add-contact.component.css']
})
export class AddContactComponent {

  user: User;
  constructor(    private route: ActivatedRoute,
                  private router: Router,
                  private contactService: ContactService) {

    this.user = new User();
  }

  onSubmit(): void {

    this.contactService.save(this.user).subscribe(result => this.gotoUserList());
  }
  gotoUserList() {
    this.router.navigate(['/contacts/admin']).then();
  }

}
