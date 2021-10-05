import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.scss'],
})
export class SaveVideoDetailsComponent implements OnInit {
  public videoForm: FormGroup;

  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private readonly formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((resp) => {
      console.log(resp);
    });
    this.videoForm = this.formBuilder.group({
      title: ['', [Validators.required]],
      description: [''],
      status: ['', [Validators.required]],
    });
  }
}
