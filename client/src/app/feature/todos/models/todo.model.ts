import { User } from "../../users/models/user.model";


export interface Todo{
    id: number;
    title: string;
    description: string;
    completed: boolean;
    User: User;
}