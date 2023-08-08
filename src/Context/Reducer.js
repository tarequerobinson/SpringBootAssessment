// import { ADD_DECK, ADD_CARD_TO_DECK } from './Actiontypes';

// const reducer = (state, action) => {
//   switch (action.type) {
//     case ADD_DECK:
//       return {
//         ...state,
//         deckList: [...state.deckList, action.payload],
//       };
      
//     case ADD_CARD_TO_DECK:
//       // Find the deck by its ID, and add the card to it.
//       const updatedDeckList = state.deckList.map(deck => {
//         if (deck.id === action.payload.deckId) {
//           return {
//             ...deck,
//             deck: [...deck.deck, action.payload.card],
//           };
//         }
//         return deck;
//       });
//       return {
//         ...state,
//         deckList: updatedDeckList,
//       };
//     // Add more cases for other action types if needed.
//     default:
//       return state;
//   }
// };

// export default reducer;
