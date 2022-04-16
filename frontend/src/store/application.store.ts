import { User } from "@/models/user";
import { defineStore } from "pinia";
import { autoConfig } from "@/api/basic-authentication.helper";
import { CreateTaskDto, TaskApi, TaskDto, TaskState, UpdateTaskDto } from "@/api/taskbook";
const taskApi = new TaskApi();

export const useStore = defineStore("taskbook", {
  state: (): State => ({
    user: undefined,
    tasks: []
  }),
  actions: {
    async loadTasks(): Promise<void> {
      const { data } = await taskApi.getTasks(autoConfig());
      this.tasks = data;
    },
    async login(user: User): Promise<void> {
      return taskApi.getTasks(autoConfig(user)).then(({ data }) => {
        this.tasks = data;
        this.user = user;
      }).catch((err) => {
        console.error(err);
        this.tasks = [];
        this.user = undefined;
      });
    },
    async logout(): Promise<void> {
      this.user = undefined;
      this.tasks = [];
    },
    async addTask(task: CreateTaskDto): Promise<void> {
      const { data } = await taskApi.addTask(task as CreateTaskDto, autoConfig());
      this.tasks.push(data);
    },
    async updateTask(id: string, task: UpdateTaskDto): Promise<void> {
      const idx = this.tasks.findIndex(t => t.id === id);
      if (idx < 0) throw Error("Task does not exist");
      const { data } = await taskApi.updateTask(id, task as UpdateTaskDto, autoConfig());
      this.tasks[idx] = data;
    }
  },
  getters: {
    authenticated: (state: State) => !!state.user,
    unfinishedTasks: (state: State) => state.tasks.filter((t) => t.status !== TaskState.FINISHED),
    finishedTasks: (state: State) => state.tasks.filter((t) => t.status === TaskState.FINISHED)
  }
});

export interface State {
  user?: User;
  tasks: TaskDto[];
}