export interface Task {
  id: number;
  title: string;
  description: string;
  status: TaskStatus;
}

export enum TaskStatus {
  PENDING,
  IN_PROGRESS,
  FINISHED,
}
