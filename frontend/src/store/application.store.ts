import { User } from "@/models/user";
import { defineStore } from "pinia";
import { autoConfig } from "@/api/basic-authentication.helper";
import {
  CreateTaskDto,
  PaginatedTaskDto,
  TaskApi,
  TaskState,
  UpdateTaskDto
} from "@/api/taskbook";
import { config } from "@/config/config";

const taskApi = new TaskApi();

export const useStore = defineStore("taskbook", {
  state: (): State => ({
    user: undefined,
    taskPage: undefined
  }),
  actions: {
    async loadTasks(page = 0, size = 25): Promise<void> {
      const { data } = await taskApi.getTasks(page, size, autoConfig());
      console.log(`loaded tasks of page ${page} with ${size} items per page`, data)
      this.taskPage = data;
    },
    async reloadTasks(): Promise<void> {
      const pagination = this.taskPage ? { size: this.taskPage.size, page: this.taskPage.page } : config.pagination;
      await this.loadTasks(pagination.page, pagination.size);
    },
    async login(user: User): Promise<void> {
      return taskApi.getTasks(config.pagination.page, config.pagination.size, autoConfig(user)).then(({ data }) => {
        this.taskPage = data;
        this.user = user;
      }).catch((err) => {
        console.error(err);
        this.taskPage = undefined;
        this.user = undefined;
      });
    },
    async logout(): Promise<void> {
      this.user = undefined;
      this.taskPage = undefined;
    },
    async addTask(task: CreateTaskDto): Promise<void> {
      await taskApi.addTask(task as CreateTaskDto, autoConfig());
      await this.reloadTasks();
    },
    async updateTask(id: string, task: UpdateTaskDto): Promise<void> {
      await taskApi.updateTask(id, task as UpdateTaskDto, autoConfig());
      await this.reloadTasks();
    },
    async deleteTask(id: string): Promise<void> {
      await taskApi.deleteTask(id, autoConfig());
      await this.reloadTasks();
    }
  },
  getters: {
    tasks: (state: State) => state.taskPage?.content ?? [],
    authenticated: (state: State) => !!state.user,
    unfinishedTasks: (state: State) => state.taskPage?.content?.filter((t) => t.status !== TaskState.FINISHED) ?? [],
    finishedTasks: (state: State) => state.taskPage?.content?.filter((t) => t.status === TaskState.FINISHED) ?? []
  }
});

export interface State {
  user?: User;
  taskPage?: PaginatedTaskDto;
}