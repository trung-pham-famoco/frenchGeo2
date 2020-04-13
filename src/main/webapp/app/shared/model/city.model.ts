export interface ICity {
  id?: number;
  name?: string;
  departmentId?: number;
}

export class City implements ICity {
  constructor(public id?: number, public name?: string, public departmentId?: number) {}
}
