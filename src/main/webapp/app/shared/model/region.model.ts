import { IDepartment } from 'app/shared/model/department.model';

export interface IRegion {
  id?: number;
  name?: string;
  prefectureId?: number;
  departments?: IDepartment[];
}

export class Region implements IRegion {
  constructor(public id?: number, public name?: string, public prefectureId?: number, public departments?: IDepartment[]) {}
}
