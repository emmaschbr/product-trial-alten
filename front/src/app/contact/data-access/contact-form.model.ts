import {FormControl} from "@angular/forms";

export interface ContactForm {
  email: FormControl<string | null>;
  message: FormControl<string | null>;
}
