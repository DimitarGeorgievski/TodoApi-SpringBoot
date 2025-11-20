import { computed, Injectable, signal } from '@angular/core';
import { Todo } from '../../feature/todos/models/todo.model';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private todos = signal<Todo[]>([]);
  selectedTodoId = signal<number>(null);
  constructor(private http: HttpClient) {
    this.getAllTodos();
  }
  todoLength = computed(() => this.todos().length);
  compltedTodos = computed(() => this.todos().filter((todos) => todos.completed));
  selectedTodo = computed(() => this.todos().find((todo) => todo.id === this.selectedTodoId()));
  getAllTodos() {
    this.http
      .get<Todo[]>('http://localhost:8080/api/todos')
      .pipe(tap((todos) => this.todos.set(todos)))
      .subscribe();
      console.log("neznaaam", this.todos());
  }
}
