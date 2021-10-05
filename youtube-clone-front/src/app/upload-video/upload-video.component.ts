import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  NgxFileDropEntry,
  FileSystemFileEntry,
  FileSystemDirectoryEntry,
} from 'ngx-file-drop';
import { from } from 'rxjs';
import { FilesService } from '../services/files.service';

@Component({
  selector: 'app-upload-video',
  templateUrl: './upload-video.component.html',
  styleUrls: ['./upload-video.component.scss'],
})
export class UploadVideoComponent implements OnInit {
  public fileUploaded: boolean = false;
  public fileEntry: FileSystemFileEntry = null;
  ngOnInit(): void {}

  constructor(
    private readonly fileService: FilesService,
    private readonly router: Router
  ) {}

  public files: NgxFileDropEntry[] = [];

  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;
    for (const droppedFile of files) {
      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
        this.fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        this.fileEntry.file((file: File) => {
          // Here you can access the real file
          console.log(droppedFile.relativePath, file);
          this.fileUploaded = true;
        });
      } else {
        // It was a directory (empty directories are added, otherwise only files)
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(droppedFile.relativePath, fileEntry);
      }
    }
  }

  public fileOver(event) {
    console.log(event);
  }

  public fileLeave(event) {
    console.log(event);
  }

  public uploadVideo() {
    if (this.fileEntry) {
      from(this.fileService.uploadVideo(this.fileEntry)).subscribe((resp) => {
        resp.subscribe(({ videoId, videoUrl }) => {
          this.router.navigateByUrl(`/save-video-details/${videoId}`);
        });
      });
    }
  }
}
