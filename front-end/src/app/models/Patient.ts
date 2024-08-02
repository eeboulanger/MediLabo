export class Patient {
  id: string;
  lastName: string;
  firstName: string;
  gender: string;
  birthdate: string;
  address: string;
  phoneNumber: string;

  constructor() {
    this.id = '';
    this.firstName = '';
    this.lastName = '';
    this.gender = '';
    this.birthdate = '';
    this.address = '';
    this.phoneNumber = '';
  }
}
