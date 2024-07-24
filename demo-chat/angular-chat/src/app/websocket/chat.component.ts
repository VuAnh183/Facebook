import { Component, OnInit } from '@angular/core';
import { AccountService, WebSocketService } from '@app/_services';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html'
})
export class ChatComponent implements OnInit {
  messages: any[] = [];
  messageContent: string = '';
  recipient: string = '';
  username: string | null = '';

  constructor(private webSocketService: WebSocketService,private accountService: AccountService) { }

  ngOnInit() {
    const user = this.accountService.userValue;
    this.username = user?.userName ? user.userName : '';
    if (this.username) {
      this.webSocketService.connect(this.username, this.messages);
    }
  }

  sendMessage() {
    const message = {
      type: 0,
      sender: this.username,
      recipient: this.recipient,
      content: this.messageContent
    };
    this.messages.push(message);
    this.webSocketService.sendMessage(message);
    this.messageContent = '';
  }
}