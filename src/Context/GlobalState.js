// import React, {createContext , useReducer, useEffect} from "react";
// import Reducer from "../Context/Reducer";
// import { ADD_CARD_TO_DECK } from "./Actiontypes";


// //initial State

// const initialState = {

//     deckList:[],
// };

// //create context
// export const GlobalContext = createContext(initialState);

// export const GlobalProvider = ({ children }) => {
//     const [state, dispatch] = useReducer(Reducer, initialState);
  


//     const addCardToDeck = (card) =>{
//         dispatch ({type: "ADD_CARD_TO_DECK" , payload: card});
//     }



//     return (
//       <GlobalContext.Provider value={{ state, dispatch , addCardToDeck, }}>
//         {children}
//       </GlobalContext.Provider>
//     );
//   };
  