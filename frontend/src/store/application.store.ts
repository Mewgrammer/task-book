import { User } from "@/models/user.interface";
import { Task, TaskStatus } from "@/models/task.interface";
import { ActionContext, createStore, useStore as baseUseStore, Store } from "vuex";
import { InjectionKey } from "vue";
import { UserCredentials } from "@/models/user-credentials.interface";

export const store = createStore<State>({
  state(): State {
    return {
      user: undefined,
      tasks: []
    };
  },
  actions: {
    async register(ctx: ActionContext<State, State>): Promise<void> {
      return ctx.dispatch("getTasks");
    },
    async login(ctx: ActionContext<State, State>, user: UserCredentials): Promise<void> {

      return ctx.commit(mutations.SET_USER, user);
    },
    async logout(ctx: ActionContext<State, State>): Promise<void> {
      return ctx.commit(mutations.SET_USER, undefined);
    },
    async getTasks(ctx: ActionContext<State, State>): Promise<void> {
      return ctx.commit(mutations.SET_TASKS, []);
    },
    async addTask(ctx: ActionContext<State, State>, task: Task): Promise<void> {
      return ctx.dispatch("getTasks");
    }
  },
  mutations: {
    setTasks(state: State, tasks: Task[]) {
      state.tasks = tasks;
    },
    setUser(state: State, user?: User) {
      state.user = user;
      state.tasks = [];
    }
  },
  getters: {
    authenticated: (state: State) => state.user && state.user.accessToken != null,
    user: (state: State) => state.user,
    unfinishedTasks: (state: State) => state.tasks.filter((t) => t.status !== TaskStatus.FINISHED),
    finishedTasks: (state: State) => state.tasks.filter((t) => t.status === TaskStatus.FINISHED)
  }
});

export const mutations = {
  SET_TASKS: "setTasks",
  SET_USER: "setUser"
};

export interface State {
  user?: User;
  tasks: Task[];
}

export const key: InjectionKey<Store<State>> = Symbol();

export function useStore(): Store<State> {
  return baseUseStore(key);
}
