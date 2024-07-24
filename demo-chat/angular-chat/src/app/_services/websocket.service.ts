import { Injectable } from '@angular/core';
import { Client, Message } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { AccountService } from './account.service';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private stompClient: Client | null = null;
  private connected: boolean = false;
  private user: any;

  constructor(private accountService: AccountService) {
    this.user = this.accountService.userValue;
  }

  connect(username: string, messages: any[]) {
    if (!this.connected) {
      const socket = new SockJS('http://localhost:8080/api/ws');
      this.stompClient = new Client({
        webSocketFactory: () => socket as WebSocket,
        connectHeaders: {
          Authorization: `Bearer ${this.user?.token}`
        }
      });

      this.stompClient.onConnect = (frame) => {
        console.log('Connected: ' + frame);
        this.connected = true;
        this.stompClient?.subscribe(`/queue/messages/${username}`, (message: Message) => {
          this.onMessageReceived(JSON.parse(message.body), messages);
        });
      };

      this.stompClient.onWebSocketClose = (evt) => {
        console.log('WebSocket closed: ' + evt);
        this.connected = false;
      };

      this.stompClient.onStompError = (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
        this.connected = false;
      };

      this.stompClient.activate();
    }
  }

  sendMessage(message: any) {
    if (this.stompClient && this.connected) {
      this.stompClient.publish({ destination: '/app/chat.send', body: JSON.stringify(message) });
    }
  }

  onMessageReceived(message: any, messages: any[]) {
    messages.push(message);
  }
}