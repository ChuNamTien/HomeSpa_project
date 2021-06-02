/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BookingService } from 'app/entities/booking/booking.service';
import { IBooking, Booking } from 'app/shared/model/booking.model';

describe('Service Tests', () => {
    describe('Booking Service', () => {
        let injector: TestBed;
        let service: BookingService;
        let httpMock: HttpTestingController;
        let elemDefault: IBooking;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(BookingService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Booking(
                0,
                0,
                0,
                currentDate,
                'AAAAAAA',
                false,
                false,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                currentDate,
                currentDate
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        startTime: currentDate.format(DATE_TIME_FORMAT),
                        createdDate: currentDate.format(DATE_TIME_FORMAT),
                        lastModifiedBy: currentDate.format(DATE_TIME_FORMAT),
                        lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Booking', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        startTime: currentDate.format(DATE_TIME_FORMAT),
                        createdDate: currentDate.format(DATE_TIME_FORMAT),
                        lastModifiedBy: currentDate.format(DATE_TIME_FORMAT),
                        lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        startTime: currentDate,
                        createdDate: currentDate,
                        lastModifiedBy: currentDate,
                        lastModifiedDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Booking(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Booking', async () => {
                const returnedFromService = Object.assign(
                    {
                        customerId: 1,
                        partnerId: 1,
                        startTime: currentDate.format(DATE_TIME_FORMAT),
                        finishTime: 'BBBBBB',
                        isFinished: true,
                        isConfirmed: true,
                        paymentMethod: 'BBBBBB',
                        confirmTime: 'BBBBBB',
                        createdBy: 'BBBBBB',
                        createdDate: currentDate.format(DATE_TIME_FORMAT),
                        lastModifiedBy: currentDate.format(DATE_TIME_FORMAT),
                        lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        startTime: currentDate,
                        createdDate: currentDate,
                        lastModifiedBy: currentDate,
                        lastModifiedDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Booking', async () => {
                const returnedFromService = Object.assign(
                    {
                        customerId: 1,
                        partnerId: 1,
                        startTime: currentDate.format(DATE_TIME_FORMAT),
                        finishTime: 'BBBBBB',
                        isFinished: true,
                        isConfirmed: true,
                        paymentMethod: 'BBBBBB',
                        confirmTime: 'BBBBBB',
                        createdBy: 'BBBBBB',
                        createdDate: currentDate.format(DATE_TIME_FORMAT),
                        lastModifiedBy: currentDate.format(DATE_TIME_FORMAT),
                        lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        startTime: currentDate,
                        createdDate: currentDate,
                        lastModifiedBy: currentDate,
                        lastModifiedDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(take(1), map(resp => resp.body))
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Booking', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
