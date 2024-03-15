export interface MessageResponse<T> {
    status: number;
    message: string;
    result: T;
  }