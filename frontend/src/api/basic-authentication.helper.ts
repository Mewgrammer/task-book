import { User } from "@/models/user";
import { AxiosBasicCredentials, AxiosRequestConfig } from "axios";
import { useStore } from "@/store/application.store";
import { config } from "@/config/config";

export function userAuthentication(user?: User): AxiosBasicCredentials {
  return {
    username: user?.username ?? "",
    password: user?.password ?? ""
  };
}

export function autoConfig(user?: User): AxiosRequestConfig {
  return {
    baseURL: config.backendUrl,
    auth: userAuthentication(user ?? useStore().user),

  };
}