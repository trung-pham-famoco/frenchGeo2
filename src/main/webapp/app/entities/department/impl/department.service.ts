import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDepartment } from 'app/shared/model/department.model';

type EntityArrayResponseType = HttpResponse<IDepartment[]>;

@Injectable({ providedIn: 'root' })
export class DepartmentService {
  public resourceUrl = SERVER_API_URL + 'api/v1/departments';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDepartment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
