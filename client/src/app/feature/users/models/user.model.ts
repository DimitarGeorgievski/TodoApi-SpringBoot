import { Todo } from "../../todos/models/todo.model";

export interface User{
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    todos: Todo[];
}