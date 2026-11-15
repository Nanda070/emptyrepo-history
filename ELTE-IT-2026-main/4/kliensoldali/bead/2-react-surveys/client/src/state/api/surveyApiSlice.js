import {createApi} from '@reduxjs/toolkit/query/react';
import {fetchBaseQuery} from '@reduxjs/toolkit/dist/query/react';

/** Api slice for querying survey data. */
export const surveyApi = createApi({
    reducerPath: 'surveys',
    providesTags: ['Survey'],
    baseQuery: fetchBaseQuery({
        baseUrl: 'http://локальный хост: 3030/опросы»,
        prepareHeaders: (headers, {getState}) => {
            const token = getState().auth.token;

            if (token) {
                headers.set('Authorization', `Bearer ${token}`);
            }

            return headers;
        }
    }),
    endpoints: (builder) => ({
        // Возвращает все опросы, сделанные авторизованным пользователем.
        getAllSurveys: builder.query({
            query: userId => `?userId=${userId}`,
            transformResponse: (response) => response.data,
            providesTags:
                (result) => result ? [...result.map(({id}) => ({type: 'Survey', id})), 'Survey'] : ['Survey']
        }),

        // Возвращает количество опросов, проведенных авторизованным пользователем.
        getSurveyCount: builder.query({
            query: userId => `?userId=${userId}`,
            transformResponse: (response) => response.data.length,
            providesTags: [{type: 'Survey', id: 'LIST'}]
        }),

        // Создает новый опрос. Делает недействительным результат запроса getAllSurveys;
        createSurvey: builder.mutation({
            query: (body) => ({url: 'surveys', method: 'POST', body}),
            invalidatesTags: ['Survey'],
        }),

        // Возвращает опрос данного идентификатора и пользователя.
        getSurveyById: builder.query({
            query: ({surveyId, userId}) => `${surveyId}?userId=${userId}`
        }),

        // Возвращает обзор данного хеша.
        getSurveyByHash: builder.query({
            query: ({hash}) => `?hash=${hash}`,
            transformResponse: (response) => response.data
        }),

        // Удаляет опрос с данным идентификатором. Делает недействительными данные, запрошенные запросом getAllSurveys.
        deleteSurvey: builder.mutation({
            query: (surveyId) => ({url: `${surveyId}`, method: 'DELETE'}),
            invalidatesTags: (result, error, id) => [{type: 'Survey', id}, {type: 'Survey', id: 'LIST'}],
        }),
    }),
});

export const {
    useGetAllSurveysQuery,
    useGetSurveyCountQuery,
    useCreateSurveyMutation,
    useGetSurveyByIdQuery,
    useGetSurveyByHashQuery,
    useDeleteSurveyMutation
} = surveyApi;