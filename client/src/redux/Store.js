// import { createStore } from 'redux';
import { createStore, applyMiddleware, compose } from 'redux';
import rootReducer from 'redux/Reducers';


window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const store = createStore(rootReducer, /* preloadedState, */ composeEnhancers(
    applyMiddleware()
  ));

// const store = createStore(rootReducer);

export default store;