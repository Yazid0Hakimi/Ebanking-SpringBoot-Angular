export interface CustomerAccounts {
  id: string;
  AccountId: string;
  balance: number;
  status: string;
  type: string;
}

export interface AccountDetails {
  accountId: string;
  balance: number;
  currentPage: number;
  totalePages: number;
  pageSize: number;
  operationDTOS: accountOperations[];
}

export interface accountOperations {
  id: number;
  operationDate: Date;
  amount: number;
  type: string;
  description: string;
}
