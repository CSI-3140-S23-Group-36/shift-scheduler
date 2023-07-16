import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { UserInfoResponse } from "./api-interfaces/responses/user-info";

// Define a type for the slice state
interface AuthState {
  userInfo?: UserInfoResponse;
}

// Define the initial state using that type
const initialState: AuthState = {
  userInfo: undefined,
};

export const authSlice = createSlice({
  name: "auth",
  // `createSlice` will infer the state type from the `initialState` argument
  initialState,
  reducers: {
    setLogin: (state, action: PayloadAction<UserInfoResponse | undefined>) => {
      state.userInfo = action.payload;
    },
  },
});

export const { setLogin } = authSlice.actions;

export default authSlice.reducer;
