import {createApi} from '@reduxjs/toolkit/query/react';
import {fetchBaseQuery} from '@reduxjs/toolkit/dist/query/react';

/** Api slice for querying survey data. */
export const deviceApi = createApi({
    reducerPath: 'devices',
    tagTypes: ['Device'],
    baseQuery: fetchBaseQuery({
        baseUrl: 'http://локальный хост: 8000/устройства'
    }),
    endpoints: (builder) => ({
        getPairedDevices: builder.query({
            query: () => '',
            providesTags: ['Device']
        }),

        getUnpairedDevices: builder.query({
            query: () => 'unpaired',
            providesTags: ['Device']
        }),

        discoverDevices: builder.mutation({
            query: () => ({
                url: 'discover',
                method: 'POST'
            }),
            invalidatesTags: ['Device']
        }),

        pairDevice: builder.mutation({
            query: (device_id) => ({
                method: 'POST',
                body: {device_id}
            }),
            invalidatesTags: ['Device']
        }),

        toggleDevice: builder.mutation({
            query: (device_id) => ({
                url: `${device_id}/toggle`,
                method: 'PATCH'
            })
        }),

        getDeviceById: builder.query({
            query: (device_id) => device_id
        })

        

        // // Создает новый опрос. Делает недействительным результат запроса getAllSurveys;
        // createSurvey: builder.mutation({
        // запрос: (body) => ({url: 'surveys', метод: 'POST', body}),
        // validatesTags: ['Опрос'],
        // }),
        //
        // // Возвращает опрос данного идентификатора и пользователя.
        // getSurveyById: builder.query({
        // запрос: ({surveyId, userId}) => `${surveyId}?userId=${userId}`
        // }),
        //
        // // Возвращает обзор данного хеша.
        // getSurveyByHash: builder.query({
        // запрос: ({hash}) => `?hash=${hash}`,
        // TransformResponse: (ответ) => ответ.данные
        // }),
        //
        // // Удаляет опрос с данным идентификатором. Делает недействительными данные, запрошенные запросом getAllSurveys.
        // deleteSurvey: builder.mutation({
        // запрос: (surveyId) => ({url: `${surveyId}`, метод: 'DELETE'}),
        // validatesTags: (result, error, id) => [{type: 'Survey', id}, {type: 'Survey', id: 'LIST'}],
        // }),
    }),
});

export const {
    useGetPairedDevicesQuery,
    useGetUnpairedDevicesQuery,
    useDiscoverDevicesMutation,
    usePairDeviceMutation,
    useToggleDeviceMutation,
    useGetDeviceByIdQuery
} = deviceApi;
