import {Component, inject, OnInit} from "@angular/core";
import {catchError} from "rxjs";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ContactForm} from "../../data-access/contact-form.model";
import {InputTextModule} from "primeng/inputtext";
import {InputTextareaModule} from "primeng/inputtextarea";
import {FloatLabelModule} from "primeng/floatlabel";
import {Button} from "primeng/button";
import {ContactService} from "../../../shared/services/contact.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    InputTextareaModule,
    FloatLabelModule,
    Button
  ],
  templateUrl: './contact-form.components.html'
})
export class ContactFormComponent implements OnInit {
  private readonly _fb = inject(FormBuilder);
  private readonly _contactService = inject(ContactService);
  private readonly _messageService = inject(MessageService);
  formGroupContact!: FormGroup<ContactForm>;

  ngOnInit() {
    this.formGroupContact = this.initForm();
  }

  /**
   * Initialisation du formulaire
   */
  initForm(): FormGroup<ContactForm> {
    return this._fb.group<ContactForm>({
      email: this._fb.nonNullable.control(null, {validators: [Validators.required, Validators.email]}),
      message: this._fb.nonNullable.control(null, {
        validators:
          [Validators.required, Validators.maxLength(300)]
      })
    });
  }

  /**
   * Envoi d'un formulaire de contact
   * @param message
   */

  onSend(): void {
    this._contactService.post({
      email: this.formGroupContact.controls.email.value!,
      message: this.formGroupContact.controls.message.value!
    }).subscribe({
      next: () => {
        this._messageService.add({severity: 'success', detail: `Demande de contact envoyée avec succès`});
        this.formGroupContact.reset();
        this.formGroupContact.markAsPristine();
        this.formGroupContact.markAsUntouched();
      },
      error: () => {
        catchError((err => {
          this._messageService.add({
            severity: 'error',
            detail: `Une erreur est survenue lors de l'envoi de votre demande`
          });
          throw err;
        }))
      }
    });
  }
}
