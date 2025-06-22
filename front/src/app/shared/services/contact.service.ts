import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Message} from "../../contact/data-access/message.model";
import {MessageService} from "primeng/api";

@Injectable({
  providedIn: "root"
})
export class ContactService {

  private readonly _http = inject(HttpClient);
  private readonly path = "/api/contact";

  public post(message: Message): Observable<Message> {
    return this._http.post<Message>(this.path, message);
  }
}
