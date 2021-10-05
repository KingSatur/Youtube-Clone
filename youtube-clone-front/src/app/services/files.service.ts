import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadVideoResponse } from '../upload-video/upload-video-response';

@Injectable({
  providedIn: 'root',
})
export class FilesService {
  constructor(private readonly httpClient: HttpClient) {}

  public uploadVideo(fileEntry: any): Promise<Observable<UploadVideoResponse>> {
    return new Promise((resolve, reject) => {
      console.log('Hola');

      fileEntry.file((file) => {
        const formData = new FormData();
        formData.append('file', file, fileEntry?.name);
        resolve(
          this.httpClient.post<UploadVideoResponse>(
            'http://localhost:8080/video',
            formData
          )
        );
      });
    });
  }
}
