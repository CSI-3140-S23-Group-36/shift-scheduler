import { Role } from "../roles";

export interface UserInfoResponse {
  id: number;
  username: string;
  name: string;
  roles: Role[];
}
